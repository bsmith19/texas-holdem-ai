(ns texas-holdem-ai.card-test
  (:require [clojure.test :refer :all]
            [texas-holdem-ai.card :refer :all]))

(deftest faceCard-test
  (testing "Checks if the given card is a facecard(or 10)"
    (is (= (faceCard {:CardVal 11}) true))
    (is (= (faceCard {:CardVal 12}) true))
    (is (= (faceCard {:CardVal 13}) true))
    (is (= (faceCard {:CardVal 14}) true))
    (is (= (faceCard {:CardVal 8}) false))))

(deftest connectedCards-test
  (testing "Check gap counting for straights"
    (is (= (connectedCards [{:CardVal 2} {:CardVal 3} {:CardVal 4} {:CardVal 5} {:CardVal 6}] 0) 4))
    (is (= (connectedCards [{:CardVal 2} {:CardVal 5} {:CardVal 6} {:CardVal 7} {:CardVal 8}] 0) 6))
    (is (= (connectedCards [{:CardVal 2} {:CardVal 5} {:CardVal 8} {:CardVal 11} {:CardVal 12}] 0) 10))))

(deftest combineLikeCards-test
  (testing "Find pairs, trips, etc"
    (is (= (combineLikeCards [{:CardVal 2 :Suite \s} {:CardVal 11 :Suite \d}] [{:CardVal 3 :Suite \d} {:CardVal 2 :Suite \s} {:CardVal 10 :Suite \d}])
        {2 [{:CardVal 2 :Suite \s} {:CardVal 2 :Suite \s}] 11 [{:CardVal 11 :Suite \d}] 3 [{:CardVal 3 :Suite \d}] 10 [{:CardVal 10 :Suite \d}]})) ))

(deftest findCollections-test
  (testing "Finds pairs, trips and quads"
    (is (= (findCollections 3 [{:CardVal 10 :Suite \s} {:CardVal 11 :Suite \c}] [{:CardVal 10 :Suite \c} {:CardVal 8 :Suite \s} {:CardVal 10 :Suite \h}]) 10))
    (is (= (findCollections 3 [{:CardVal 10 :Suite \s} {:CardVal 11 :Suite \c}] [{:CardVal 10 :Suite \c} {:CardVal 8 :Suite \s} {:CardVal 11 :Suite \h}]) 0)) ))
