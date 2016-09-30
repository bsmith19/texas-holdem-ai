(ns texas-holdem-ai.match-test
  (:require [clojure.test :refer :all]
            [texas-holdem-ai.match :refer :all]
            [texas-holdem-ai.state :refer :all]))


(deftest parseStateMatch-test
  (testing "Verify cards get converted to data from string when stored"
    (is (= (parseStateMatch "table" "[As,Td,4c]" {:board []}) {:gameState STATE-FLOP :board [{:CardVal 14 :Suite \s} {:CardVal 10 :Suite \d} {:CardVal 4 :Suite \c}]}))))
