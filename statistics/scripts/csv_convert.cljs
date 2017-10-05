#!/usr/bin/env lumo

(ns e-commerce-demo.cvs-convert
  (:require [lumo.io :refer [slurp spit]]
            [clojure.string :as str]))

(defn break-line [line]
  (as-> line x
    (str/split x #" ")
    (filter #(not= 0 (count %)) x)))

(defn valid-collection [c]
  (let [floats (mapv #(js/parseFloat %) c)
        all-floats? (reduce #(and %1 (not (js/isNaN %2))) true floats)]
    (if (and all-floats? (= 4 (count floats)))
      floats
      nil)))

(defn filter-line [line]
  (-> line
      break-line
      valid-collection))

(defn data-collection [lines]
  (reduce (fn [c line]
            (if-let [floats (filter-line line)]
              (conj c floats)
              c))
          []
          lines))

(defn create-csv [coll]
  (reduce (fn [s floats]
            (str s (str/join "," floats) "\n"))
          ""
          coll))

(def files ["0ms-delay"
            "100ms-delay-100ms-stddev-plus-acting-up-2000x2000-with-simple-hystrix"
            "100ms-delay-100ms-stddev-plus-acting-up-2000x2000"
            "100ms-delay-100ms-stddev"
            "100ms-delay"
            "2x100ms-delay-100ms-stddev"
            "2x100ms-delay"])

(doseq [file files]
  (let [csv (-> (str "../histograms/" file ".hgrm")
                slurp
                (str/split #"\n")
                data-collection
                create-csv)]
    (spit (str "../histograms/" file ".csv") csv)))
