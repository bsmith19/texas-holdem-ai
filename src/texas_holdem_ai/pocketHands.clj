(ns texas-holdem-ai.pocketHands
  (:use [texas-holdem-ai state card])
  (:gen-class))


(defn pocketPairs?
  [hand]
  (if (= ((hand 0) :CardVal) ((hand 1) :CardVal)) [true ((hand 0) :CardVal)] [false nil]))

(defn pocketFlush?
  [hand]
  (if (= ((hand 0) :Suite) ((hand 1) :Suite))
    [true ((hand 1) :Suite)]
    [false nil]))

(defn pocketStraight?
  [hand]
  (if (or (= ((hand 0) :CardVal) (+ ((hand 1) :CardVal) 1)) (= ((hand 0) :CardVal) (- ((hand 1) :CardVal) 1)))
    [true (max ((hand 0) :CardVal) ((hand 1) :CardVal))]
    [false nil]))

(defn pocketStraightFlush?
  [hand]
  (let [flushData (pocketFlush? hand) straightData (pocketStraight? hand)]
    (if (and (= (flushData 0) true) (= (straightData 0) true))
      [true (flushData 1) (straightData 1)]
      [false nil nil])))

(defn pocketHighHand?
  [hand]
  (if (and (faceCard (hand 0)) (faceCard (hand 1))) [true (max ((hand 0) :CardVal) ((hand 1) :CardVal))] [false nil]))
