(ns texas-holdem-ai.handParse-test
  (:require [clojure.test :refer :all]
            [texas-holdem-ai.handParse :refer :all]))

(deftest handParser-test
  (testing "Removes brackets, cuts string, puts in dictionary"
    (is (= (handParser "[Kc,4h,As]") [{:CardVal 13 :Suite \c} {:CardVal 4 :Suite \h} {:CardVal 14 :Suite \s}]))))

(deftest removeBrackets-test
  (testing "Removes brackets [] from string"
    (is (= (removeBrackets "[test]") "test"))))

(deftest buildCardList-test
  (testing "Cuts string at commas"
    (is (= (buildCardList "Kc,4h,As") ["Kc","4h","As"]))))

(deftest parseCardPair-test
  (testing "Removes brackets, cuts string, puts in dictionary"
    (is (= (parseCardPair "Kc") {:CardVal 13 :Suite \c}))))
