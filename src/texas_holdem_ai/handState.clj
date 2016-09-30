(ns texas-holdem-ai.handState
  (:use [texas-holdem-ai settings handParse state])
  (:gen-class))

;;This process and stores all mid hand moves and changes to player state

;;b stack i -> The amount of chips that the given bot has left in his stack
;;b post i -> The given bot pays a blind of the given size
;;b hand [c,c] -> The hand that the given bot has
;;b fold 0 -> The given bot folds, always followed by the number zero
;;b check 0 -> The given bot checks, always followed by the number zero
;;b call i -> The given bot calls with the given amount of chips
;;b raise i -> The given bot raises with the given amount of chips
;;b wins i -> The given bot wins an amount of chips at the end of a hand

(defn your_botMoves
  [action value currentState]
  (case action
    "hand" (conj currentState {:hand (handParser value) :gameState STATE-PREFLOP})
    "stack" (conj currentState {:stack (Integer. value)})
    "post" (if (= value (get-in currentState [:matchInfo :big_blind]))
            (conj currentState {:isBB true})
            (conj currentState {:isBB false}))
    "wins" (conj currentState {:board [] :hand []})
    currentState))

(defn opp_botMoves
  [action value currentState]
  (case action
    "wins" (conj currentState {:board [] :hand []})
    (conj currentState {:last_opp_move action :last_opp_val value})))

(defn parseStateHand
  [botName action value currentState]
  (condp = botName
    (your_bot currentState) (your_botMoves action value currentState)
    (opp_bot currentState) (opp_botMoves action value currentState)))


(defn getMyStack
  [currentState]
  (get currentState :stack))

(defn getMyHand
  [currentState]
  (get currentState :hand))
