(ns texas-holdem-ai.brain
  (:use [texas-holdem-ai state card match commonHands pocketHands handState bets util])
  (:gen-class))

;;This file orginates all computation in regards to holdem hands

;;Can i call for free?
(defn canCheck
  [currentState]
  (if (= (getAmountToCall currentState) 0) true false))

(defn checkPreflop
  [currentState]
  (let [pocket (getMyHand currentState) pairs (pocketPairs? pocket) flush (pocketFlush? pocket) straight
    (pocketStraight? pocket) straightflush (pocketStraightFlush? pocket) highHand (pocketHighHand? pocket)]
    (cond
      (pairs 0) (betPreflop "pair" pairs currentState)
      (highHand 0) (betPreflop "highHand" highHand currentState)
      (straightflush 0) (betPreflop "straightflush" straightflush currentState)
      (flush 0) (betPreflop "flush" flush currentState)
      (straight 0) (betPreflop "straight" straight currentState)
      true 0)))

(defn checkFlop
  [currentState]
  (let [pocket (getMyHand currentState) board (getBoard currentState) pairs (pair? pocket board) flush (flush? pocket board) straight
    (straight? pocket board) straightflush (straightflush? pocket board) trips (trips? pocket board) quads (quads? pocket board)
    fullhouse (fullhouse? pocket board) twopair (twopair? pocket board)]
    (cond
      (straightflush 0) (betFlop "straightflush" straightflush currentState)
      (quads 0) (betFlop "quads" quads currentState)
      (fullhouse 0) (betFlop "fullhouse" fullhouse currentState)
      (flush 0) (betFlop "flush" flush currentState)
      (some #{true} straight) (betFlop "straight" straight currentState)
      (trips 0) (betFlop "trips" trips currentState)
      (twopair 0) (betFlop "twopair" twopair currentState)
      (= 4 (flush 1)) (betFlop "4flush" flush currentState)
      (pairs 0) (betFlop "pair" pairs currentState)
      true 0)))

(defn checkTurn
  [currentState]
  (let [pocket (getMyHand currentState) board (getBoard currentState) pairs (pair? pocket board) flush (flush? pocket board) straight
    (straight? pocket board) straightflush (straightflush? pocket board) trips (trips? pocket board) quads (quads? pocket board)
    fullhouse (fullhouse? pocket board) twopair (twopair? pocket board)]
    (cond
      (straightflush 0) (betTurn "straightflush" straightflush currentState)
      (quads 0) (betTurn "quads" quads currentState)
      (fullhouse 0) (betTurn "fullhouse" fullhouse currentState)
      (flush 0) (betTurn "flush" flush currentState)
      (some #{true} straight) (betTurn "straight" straight currentState)
      (trips 0) (betTurn "trips" trips currentState)
      (twopair 0) (betTurn "twopair" twopair currentState)
      (= 4 (flush 1)) (betTurn "4flush" flush currentState)
      (pairs 0) (betTurn "pair" pairs currentState)
      true 0)))

(defn checkRiver
  [currentState]
  (let [pocket (getMyHand currentState) board (getBoard currentState) pairs (pair? pocket board) flush (flush? pocket board) straight
    (straight? pocket board) straightflush (straightflush? pocket board) trips (trips? pocket board) quads (quads? pocket board)
    fullhouse (fullhouse? pocket board) twopair (twopair? pocket board)]
    (cond
      (straightflush 0) (betRiver "straightflush" straightflush currentState)
        (quads 0) (betRiver "quads" quads currentState)
        (fullhouse 0) (betRiver "fullhouse" fullhouse currentState)
        (flush 0) (betRiver "flush" flush currentState)
        (some #{true} straight) (betRiver "straight" straight currentState)
        (trips 0) (betRiver "trips" trips currentState)
        (twopair 0) (betRiver "twopair" twopair currentState)
        (pairs 0) (betRiver "pair" pairs currentState)
        true 0)))

(defn verifyAmount
  [amt currentState]
  (let [raiseAmount (- amt (getAmountToCall currentState))]
    (cond
      (= raiseAmount 0) ["call" "0"]
      (> raiseAmount 0) ["raise" (str raiseAmount)]
      (< raiseAmount 0) ["fold" "0"])))

(defn brain
  [currentState]
  (condp = (getHandState currentState)
    STATE-PREFLOP (verifyAmount (checkPreflop currentState) currentState)
    STATE-FLOP (verifyAmount (checkFlop currentState) currentState)
    STATE-TURN (verifyAmount (checkTurn currentState) currentState)
    STATE-RIVER (verifyAmount (checkRiver currentState) currentState)
    STATE-NONE ["fold" "0"]))
