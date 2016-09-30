(ns texas-holdem-ai.handState-test
  (:require [clojure.test :refer :all]
            [texas-holdem-ai.handState :refer :all]))

(deftest your_botMoves-test
  (testing "Test your bot actions"
    (is (= (your_botMoves "hand" "[As,Ad]" {}) {:hand [{:CardVal 14 :Suite \s},{:CardVal 14 :Suite \d}] :gameState 1}))
    (is (= (your_botMoves "stack" "2000" {}) {:stack 2000}))
    (is (= (your_botMoves "post" "20" {:matchInfo {:big_blind "20"}}) {:isBB true :matchInfo {:big_blind "20"}}))
    (is (= (your_botMoves "fold" "0" {}) {}))
    (is (= (your_botMoves "wins" "200" {:hand [1,2,3] :board [1,3,4]}) {:hand [] :board []}))))
