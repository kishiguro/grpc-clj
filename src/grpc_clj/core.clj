(ns grpc-clj.core
  (:use [grpc-clj.client]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defonce server-atom (atom nil))

(defn start-server []
  (when-not @server-atom
    (reset! server-atom
            (-> (io.grpc.ServerBuilder/forPort 3000)
                (.addService (new grpc-clj.server.RouteGuideServer))
                (.build)
                (.start)))))

(defn stop-server []
  (when @server-atom
    (.shutdown @server-atom)
    (reset! server-atom nil)))

(defn restart-server []
  (stop-server)
  (start-server))
