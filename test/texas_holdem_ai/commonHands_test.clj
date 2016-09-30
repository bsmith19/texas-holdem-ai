(ns texas-holdem-ai.commonHands-test
  (:require [clojure.test :refer :all]
            [texas-holdem-ai.commonHands :refer :all]))


(deftest straight-options-test
  (testing "Can show you how close you are to straight at all streets"
    (is (= (straight-options [{:CardVal 2} {:CardVal 4}] [{:CardVal 3} {:CardVal 5} {:CardVal 6}]) [4]))
    (is (= (straight-options [{:CardVal 2} {:CardVal 4}] [{:CardVal 3} {:CardVal 5} {:CardVal 6} {:CardVal 3}]) [4 4]))
    (is (= (straight-options [{:CardVal 2} {:CardVal 4}] [{:CardVal 3} {:CardVal 5} {:CardVal 6} {:CardVal 7} {:CardVal 10}]) [4 5 8]))
    (is (= (straight-options [{:CardVal 2} {:CardVal 4}] []) [2])) ))

(deftest straight?-test
  (testing "Do you have a straight now? or maybe later?"
  ;(is (= (straight? [{:CardVal 9} {:CardVal 10}] []) [true]))
  (is (= (straight? [{:CardVal 9} {:CardVal 10}] [{:CardVal 2} {:CardVal 3} {:CardVal 4}]) [false]))
  (is (= (straight? [{:CardVal 2} {:CardVal 5}] [{:CardVal 7} {:CardVal 3} {:CardVal 4} {:CardVal 6}]) [true true]))
  (is (= (straight? [{:CardVal 3} {:CardVal 14}] [{:CardVal 2} {:CardVal 4} {:CardVal 6} {:CardVal 6} {:CardVal 7}]) [false false false])) )) ;;Edit this later for 1 card handParse-test

(deftest flush?-test
  (testing "Are there 5 cards of the same suite"
    (is (= (flush? [{:CardVal 2 :Suite \d} {:CardVal 6 :Suite \d}] [{:CardVal 10 :Suite \d} {:CardVal 3 :Suite \d} {:CardVal 8 :Suite \d}]) [true 5]))
    (is (= (flush? [{:CardVal 2 :Suite \s} {:CardVal 6 :Suite \d}] [{:CardVal 10 :Suite \c} {:CardVal 3 :Suite \d} {:CardVal 8 :Suite \d}]) [false 3])) ))

(deftest quads?-test
  (testing "Are there 4 cards of the same value"
    (is (= (quads? [{:CardVal 2 :Suite \d} {:CardVal 2 :Suite \c}] [{:CardVal 2 :Suite \s} {:CardVal 2 :Suite \h} {:CardVal 8 :Suite \d}]) [true 2])) ))

(deftest twopair?-test
  (testing "Are 2 pair being correctly picked up?"
    (is (= (twopair? [{:CardVal 10 :Suite \s} {:CardVal 11 :Suite \s}] [{:CardVal 11 :Suite \d} {:CardVal 10 :Suite \d} {:CardVal 8 :Suite \s}]) [true 11 10]))
    (is (= (twopair? [{:CardVal 10 :Suite \s} {:CardVal 11 :Suite \s}] [{:CardVal 11 :Suite \d} {:CardVal 9 :Suite \s} {:CardVal 7 :Suite \h}]) [false nil nil])) ))
