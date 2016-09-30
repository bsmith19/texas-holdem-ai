(ns texas-holdem-ai.state-test
  (:require [clojure.test :refer :all]
            [texas-holdem-ai.state :refer :all]))

(deftest computeStateByBoard-test
  (testing "Verify state is correct from board size"
    (is (= (computeStateByBoard [{:CardVal 14 :Suite \s} {:CardVal 14 :Suite \c} {:CardVal 14 :Suite \d}]) STATE-FLOP))
    (is (= (computeStateByBoard [{:CardVal 14 :Suite \s} {:CardVal 14 :Suite \s} {:CardVal 14 :Suite \s} {:CardVal 13 :Suite \s}]) STATE-TURN))
    (is (= (computeStateByBoard [{:CardVal 14 :Suite \s} {:CardVal 14 :Suite \s} {:CardVal 14 :Suite \s} {:CardVal 12 :Suite \s} {:CardVal 13 :Suite \s}]) STATE-RIVER))))
