(ns grpc-clj.util
  (:import [io.grpc.examples.routeguide Feature Point Rectangle RouteNote])
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]))

(defn point->map [point]
  (let [longitude (.getLongitude point)
        latitude (.getLatitude point)]
    {"longitude" longitude
     "latitude" latitude}))

(defn rectangle->map [rect]
  (let [lo (.getLo rect)
        hi (.getHi rect)]
    {"lo" (point->map lo) "hi" (point->map hi)}))

(defn map->point [map]
  (let [{:strs [longitude latitude]} map]
    (-> (Point/newBuilder)
        (.setLongitude longitude)
        (.setLatitude latitude)
        (.build))))

(defn map->feature [map]
  (let [{:strs [name location]} map
        point (map->point location)]
    (-> (Feature/newBuilder)
        (.setName name)
        (.setLocation point)
        (.build))))

(defn map->rectangle [map]
  (let [{:strs [lo hi]}  map]
    (-> (Rectangle/newBuilder)
        (.setLo (map->point lo))
        (.setHi (map->point hi))
        (.build))))

(defn map->route-note [map]
  (let [{:strs [location message]} map
        point (map->point location)]
    (-> (RouteNote/newBuilder)
        (.setLocation point)
        (.setMessage message)
        (.build))))

(defn get-default-features-file []
  (io/resource "route_guide_db.json"))

(defn parse-features [url]
  (let [file (io/file url)]
    ((json/read-str (slurp file)) "feature")))

(defn feature-list []
  (parse-features (get-default-features-file)))

(defn check-feature [point]
  (let [feature (first (filter #(= (get % "location") point)
                               (feature-list)))]
    (if feature
      feature
      {"name" "" "location" point})))

(defn list-features [rect]
  (feature-list))

(defn put-item [s]
  (when (seq s)
    (println (first s))
    (recur (rest s))))
