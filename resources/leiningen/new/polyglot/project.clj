(defproject {{raw-name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :min-lein-version "2.0.0"
  :source-paths ["src/clojure"]
  :test-paths ["test/clojure"]
  :java-source-paths ["src/java"]
  :javac-options ["-target" "1.8" "-source" "1.8"]
  :resource-paths ["resources"]
  :profiles {:java-test {:java-source-paths ["src/java" "test/java"]}
             :debug {:debug true
                     :injections [(prn (into {} (System/getProperties)))]}
             :dev {:aliases {"run-junit" ["with-profile" "+java-test"
                                          "trampoline" "run" "-m" "org.junit.platform.console.ConsoleLauncher"
                                          "--scan-class-path"]}
                   :plugins [[refactor-nrepl "2.3.1"]]
                   :dependencies [[org.junit.platform/junit-platform-console-standalone "1.0.2"]
                                  [org.junit.jupiter/junit-jupiter-api "5.0.2"]
                                  [org.junit.jupiter/junit-jupiter-engine "5.0.2"]
                                  [org.junit.jupiter/junit-jupiter-params "5.0.2"]
                                  [org.apiguardian/apiguardian-api "1.0.0"]]}
             :uberjar {:disable-implicit-clean true
                       :aot [{{namespace}}]}}
  :main {{namespace}}.{{java-class-name}})

