(ns texas-holdem-ai.bets
  (:use [texas-holdem-ai state match settings handState util])
  (:gen-class))

(defn betPreflop
  [handType handVal currentState]
  (let [bigBlind (getBigBlind currentState) stack (getMyStack currentState)]
    (case handType
      "pair" (case (handVal 1)
              14 stack
              13 (* 5 bigBlind)
              12 (* 4 bigBlind)
              11 (* 2 bigBlind)
              10 (* 2 bigBlind)
              (* 1 bigBlind))
      "flush" (* 3 bigBlind)
      "straight" 0
      "straightflush" (* 2 bigBlind)
      "highHand" (case (handVal 1)
                  14 (* 5 bigBlind)
                  13 (* 4 bigBlind)
                  (* 2 bigBlind))
      0)))

(defn betFlop
  [handType handVal currentState]
  (let [bigBlind (getBigBlind currentState) stack (getMyStack currentState)]
    (case handType
      "pair" (case (handVal 1)
              14 (* 3 bigBlind)
              13 (* 3 bigBlind)
              12 (* 2 bigBlind)
              11 (* 2 bigBlind)
              10 (* 2 bigBlind)
              (* 1 bigBlind))
      "4flush" (* 0.2 stack)
      "flush" (* 0.3 stack)
      "straight" (* 0.5 stack)
      "straightflush" stack
      "highHand" 0
      "trips" (* 0.15 stack)
      "quads" stack
      "fullhouse" (* 0.5 stack)
      "twopair" (* 4 bigBlind)
      0)))


(defn betTurn
  [handType handVal currentState]
  (let [bigBlind (getBigBlind currentState) stack (getMyStack currentState)]
    (case handType
      "pair" (case (handVal 1)
              14 (* 3 bigBlind)
              13 (* 2 bigBlind)
              12 (* 2 bigBlind)
              11 (* 1 bigBlind)
              10 (* 1 bigBlind)
              (* 1 bigBlind))
      "4flush" (* 0.15 stack)
      "flush" (* 0.3 stack)
      "straight" (* 0.5 stack)
      "straightflush" stack
      "highHand" 0
      "trips" (* 10 bigBlind)
      "quads" stack
      "fullhouse" (* 0.5 stack)
      "twopair" (* 4 bigBlind)
      0)))


(defn betRiver
  [handType handVal currentState]
  (let [bigBlind (getBigBlind currentState) stack (getMyStack currentState)]
    (case handType
      "pair" (case (handVal 1)
              14 (* 3 bigBlind)
              13 (* 2 bigBlind)
              12 (* 2 bigBlind)
              11 (* 1 bigBlind)
              10 (* 1 bigBlind)
              (* 1 bigBlind))
      "flush" (* 0.3 stack)
      "straight" (* 0.5 stack)
      "straightflush" stack
      "highHand" 0
      "trips" (* 8 bigBlind)
      "quads" stack
      "fullhouse" (* 0.5 stack)
      "twopair" (* 3 bigBlind)
      0)))
