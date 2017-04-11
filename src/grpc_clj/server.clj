(ns grpc-clj.server
  (:gen-class
   :name grpc-clj.server.RouteGuideServer
   :extends io.grpc.examples.routeguide.RouteGuideGrpc$RouteGuideImplBase)
  (:import [io.grpc.stub StreamObserver])
  (:use [grpc-clj.util]))

(defn -getFeature [this req res]
  (.onNext res (map->feature (check-feature (point->map req))))
  (.onCompleted res))

(defn -listFeatures [this req res]
  (doseq [feature (list-features (rectangle->map req))]
    (.onNext res (map->feature feature)))
  (.onCompleted res))

(defn -recordRoute [this res]
  (reify StreamObserver
    (onNext [this point]
      (println point res))
    (onError [this err]
      (println err))
    (onCompleted [this]
      (println "onCompleted server")
      ;; there will be one .onNext here
      (.onCompleted res))))

(defn -routeChat [this res]
  (reify StreamObserver
    (onNext [this note]
      ;; there will be multiple .onNext here
      (println note))
    (onError [this err]
      (println err))
    (onCompleted [this]
      (println "onCompleted server")
      ;; there will be no .onNext here
      (.onCompleted res))))
