(ns texas-holdem-ai.pocketHands-test
  (:require [clojure.test :refer :all]
            [texas-holdem-ai.pocketHands :refer :all]))

(deftest pocketPair?-test
  (testing "Verify if pocket pair detection works"
    (is (= (pocketPairs? [{:CardVal 14 :Suite \d} {:CardVal 14 :Suite \s}]) [true 14]))
    (is (= (pocketPairs? [{:CardVal 13 :Suite \d} {:CardVal 14 :Suite \s}]) [false nil]))))

(deftest pocketFlush?-test
  (testing "Verify if pocket pair detection works"
    (is (= (pocketFlush? [{:CardVal 14 :Suite \d} {:CardVal 14 :Suite \d}]) [true \d]))
    (is (= (pocketFlush? [{:CardVal 13 :Suite \d} {:CardVal 14 :Suite \s}]) [false nil]))))

(deftest pocketStraight?-test
  (testing "Verify if pocket pair detection works"
    (is (= (pocketStraight? [{:CardVal 14 :Suite \d} {:CardVal 13 :Suite \s}]) [true 14]))
    (is (= (pocketStraight? [{:CardVal 13 :Suite \d} {:CardVal 14 :Suite \s}]) [true 14]))
    (is (= (pocketStraight? [{:CardVal 10 :Suite \s} {:CardVal 8 :Suite \c}]) [false nil]))))

(deftest pocketStraightFlush?-test
  (testing "Verify if pocket pair detection works"
    (is (= (pocketStraightFlush? [{:CardVal 14 :Suite \d} {:CardVal 13 :Suite \d}]) [true \d 14]))
    (is (= (pocketStraightFlush? [{:CardVal 13 :Suite \d} {:CardVal 14 :Suite \s}]) [false nil nil]))))

(deftest pocketHighHand?-test
  (testing "Verify if pocket pair detection works"
    (is (= (pocketHighHand? [{:CardVal 14 :Suite \d} {:CardVal 13 :Suite \s}]) [true 14]))
    (is (= (pocketHighHand? [{:CardVal 8 :Suite \d} {:CardVal 2 :Suite \s}]) [false nil]))))
