(ns texas-holdem-ai.state
  (:gen-class))

;;Util Functions
(defn getHand
  [currentState]
  (get currentState :hand))

;; Defines for the possible game states to avoid magic numbers
(def STATE-NONE 0)
(def STATE-PREFLOP 1)
(def STATE-FLOP 2)
(def STATE-TURN 3)
(def STATE-RIVER 4)

;;Compute state by board state
(defn computeStateByBoard
  [board]
  (condp = (count board)
    0 STATE-PREFLOP
    3 STATE-FLOP
    4 STATE-TURN
    5 STATE-RIVER))

;;Shortcut to get the current hand state
(defn getHandState
  [currentState]
  (get currentState :gameState))

;;Shortcut to set a updated hand state
(defn setHandState
  [currentState newHandState]
  (assoc currentState :gameState newHandState))
