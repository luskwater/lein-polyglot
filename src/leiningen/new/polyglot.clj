(ns leiningen.new.polyglot
  (:require
   [leiningen.new.templates :as tpl
    :refer [renderer year date name-to-path ->files
            multi-segment sanitize-ns project-name]]
   [leiningen.core.main :as main]
   [clojure.string :as str]))

(def render (renderer "polyglot"))

(defn java-class-name
  "Render a Clojure-y name as a Java `CamelCase` name

  The input is assumed to be like `my-special-name` and should be
  resolved to `MySpecialName`"
  [s]
  (-> s
      (str/replace #"^([A-Za-z])" #(str/upper-case (second %)))
      (str/replace #"-+([A-Za-z])" #(str/upper-case (second %)))))

(defn polyglot
  "FIXME: write documentation"
  [name]
  (let [main-ns (multi-segment (sanitize-ns name))
        simple-name (project-name name)
        data {:raw-name name
              :name simple-name
              :namespace main-ns
              :nested-dirs (name-to-path main-ns)
              :java-class-name (java-class-name simple-name)
              :year (year)
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' polyglot project.")
    (main/info (str "Execute `chmod +x run-"
                    simple-name ".sh` followed by `./run-"
                    simple-name ".sh` to run the Java code"))
    (main/info "Execute `lein run-junit` to run the Java tests")
    (->files data
             ["run-{{name}}.sh" (render "run-java.sh" data)]
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render "gitignore" data)]
             [".hgignore" (render "hgignore" data)]
             ["src/clojure/{{nested-dirs}}.clj" (render "core.clj" data)]
             ["test/clojure/{{nested-dirs}}_test.clj" (render "test.clj" data)]
             ["src/java/{{nested-dirs}}/{{java-class-name}}.java" (render "core.java" data)]
             ["test/java/{{nested-dirs}}{{java-class-name}}Test.java" (render "test.java" data)]
             ["doc/intro.md" (render "intro.md" data)]
             ["LICENSE" (render "LICENSE" data)])))
