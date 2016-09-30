(ns texas-holdem-ai.brain-test
  (:require [clojure.test :refer :all]
            [texas-holdem-ai.brain :refer :all]
            [texas-holdem-ai.state :refer :all]))

(deftest brain-test
  (testing "Verify Preflop"
    (is (= (brain {:gameState 1 :stack 2000 :hand [{:CardVal 14 :Suite \s} {:CardVal 14 :Suite \d}] :matchInfo {:amount_to_call 0 :big_blind 20}}) ["raise" "2000"]))
    (is (= (brain {:gameState 1 :stack 2000 :hand [{:CardVal 9 :Suite \s} {:CardVal 2 :Suite \d}] :matchInfo {:amount_to_call 10 :big_blind 20}}) ["fold" "0"]))
    (is (= (brain {:gameState 1 :stack 2000 :hand [{:CardVal 9 :Suite \s} {:CardVal 2 :Suite \d}] :matchInfo {:amount_to_call 0 :big_blind 20}}) ["call" "0"]))
    (is (= (brain {:gameState 1 :stack 2000 :hand [{:CardVal 9 :Suite \s} {:CardVal 2 :Suite \s}] :matchInfo {:amount_to_call 10 :big_blind 20}}) ["raise" "50"]))
    (is (= (brain {:gameState 1 :stack 2000 :hand [{:CardVal 10 :Suite \s} {:CardVal 11 :Suite \s}] :matchInfo {:amount_to_call 40 :big_blind 20}}) ["call" "0"]))
    (is (= (brain {:gameState 1 :stack 2000 :hand [{:CardVal 9 :Suite \s} {:CardVal 8 :Suite \d}] :matchInfo {:amount_to_call 10 :big_blind 20}}) ["fold" "0"]))
    (is (= (brain {:gameState 1 :stack 2000 :hand [{:CardVal 13 :Suite \c} {:CardVal 10 :Suite \d}] :matchInfo {:amount_to_call 10 :big_blind 20}}) ["raise" "70"])) )
  (testing "Verify Flop"
    (is (= (brain {:gameState 2 :stack 2000 :hand [{:CardVal 9 :Suite \s} {:CardVal 9 :Suite \d}]
                    :board [{:CardVal 2 :Suite \s} {:CardVal 9 :Suite \c} {:CardVal 9 :Suite \h}] :matchInfo {:amount_to_call 10 :big_blind 20}}) ["raise" "1990"]))
    (is (= (brain {:gameState 2 :stack 2000 :hand [{:CardVal 11 :Suite \s} {:CardVal 10 :Suite \d}]
                    :board [{:CardVal 11 :Suite \h} {:CardVal 10 :Suite \c} {:CardVal 10 :Suite \h}] :matchInfo {:amount_to_call 1500 :big_blind 20}}) ["fold" "0"]))
    (is (= (brain {:gameState 2 :stack 2000 :hand [{:CardVal 13 :Suite \s} {:CardVal 10 :Suite \d}]
                    :board [{:CardVal 2 :Suite \s} {:CardVal 9 :Suite \c} {:CardVal 10 :Suite \h}] :matchInfo {:amount_to_call 0 :big_blind 20}}) ["raise" "40"])) )
  (testing "Verify Turn"
    (is (= (brain {:gameState 3 :stack 2000 :hand [{:CardVal 13 :Suite \s} {:CardVal 10 :Suite \d}]
                    :board [{:CardVal 2 :Suite \s} {:CardVal 10 :Suite \c} {:CardVal 10 :Suite \h} {:CardVal 11 :Suite \d}] :matchInfo {:amount_to_call 30 :big_blind 20}})
                    ["raise" "170"]))
    (is (= (brain {:gameState 3 :stack 2000 :hand [{:CardVal 6 :Suite \s} {:CardVal 7 :Suite \d}]
                    :board [{:CardVal 2 :Suite \s} {:CardVal 8 :Suite \c} {:CardVal 10 :Suite \h} {:CardVal 9 :Suite \d}] :matchInfo {:amount_to_call 0 :big_blind 20}})
                    ["raise" "1000.0"])) ))
