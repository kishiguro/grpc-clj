(defproject grpc-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [io.grpc/grpc-core "1.1.2"]
                 [io.grpc/grpc-netty "1.1.2" :exclusions [io.grpc/grpc-core]]
                 [io.grpc/grpc-protobuf "1.1.2"]
                 [io.grpc/grpc-stub "1.1.2"]
                 [org.clojure/data.json "0.2.6"]]
  :main ^:skip-aot grpc-clj.core
  :aot [grpc-clj.server]
  :target-path "target/%s"
  :java-source-paths ["build/generated/source/proto/main"]
  :profiles {:uberjar {:aot :all}})
