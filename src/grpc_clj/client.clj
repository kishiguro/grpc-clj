(ns grpc-clj.client
  (:use [grpc-clj.util])
  (:import [io.grpc.stub StreamObserver]))

(defn channel []
  (-> (io.grpc.ManagedChannelBuilder/forAddress "localhost" 3000)
      (.usePlaintext true)
      (.build)))

(defn blocking-stub []
  (io.grpc.examples.routeguide.RouteGuideGrpc/newBlockingStub (channel)))

(defn async-stub []
  (io.grpc.examples.routeguide.RouteGuideGrpc/newStub (channel)))

(defn get-feature [point]
  (.getFeature (blocking-stub)
               (map->point point)))

(defn get-list-features [rect]
  (let [features (.listFeatures (blocking-stub)
                                (map->rectangle rect))]
    (while (.hasNext features)
      (println (.next features)))))

(defn response-observer []
  (reify StreamObserver
    (onNext [this summary]
      (println summary))
    (onError [this err]
      (println err))
    (onCompleted [this]
      (println "onCompleted client"))))

(defn get-record-route []
  (let [req (.recordRoute (async-stub) (response-observer))]
    (.onNext req (map->point {"longitude" 100 "latitude" 200}))
    (.onCompleted req)))

(defn route-chat []
  (let [req (.routeChat (async-stub) (response-observer))]
    (.onNext req (map->route-note {"message" "hello" "location" {"longitude" 99 "latitude" 98}}))
    (.onNext req (map->route-note {"message" "hello" "location" {"longitude" 99 "latitude" 98}}))
    (.onCompleted req)))

(defn get-feature-test1 []
  (get-feature {"longitude" -746143763 "latitude" 407838351}))

(defn get-feature-test2 []
  (get-feature {"longitude" -746143763 "latitude" 407838352}))
(defn get-list-features-test []
  (get-list-features {"lo" {"longitude" 100 "latitude" 200} "hi" {"longitude" 200 "latitude" 300}}))
