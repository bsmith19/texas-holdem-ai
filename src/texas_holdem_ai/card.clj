(ns texas-holdem-ai.card
  (:use [texas-holdem-ai util])
  (:gen-class))

(defn faceCard
  [card]
  (if (or (= (card :CardVal) 11) (= (card :CardVal) 12) (= (card :CardVal) 13) (= (card :CardVal) 14) (= (card :CardVal) 10)) true false))

;;Straight computation stuff
;;Must come in to this function sorted!
(defn connectedCards
  [cards holes]
  (cond
    (empty? cards) holes
    (= (second cards) nil) (connectedCards [] holes)
    true (connectedCards (rest cards) (+ holes (- ((second cards) :CardVal) ((first cards) :CardVal))))))

(defn connectedCards2
  [cards spot]
  (cond
    (empty? cards) true
    (= spot 4) true
    true (if (= (+ ((first cards) :CardVal) 1) ((second cards) :CardVal))
          (connectedCards2 (rest cards) (+ 1 spot))
          false)))

;;Board Pairs+
(defn combineLikeCards
  [pocket board]
  (group-by :CardVal (concat pocket board)))

(defn largestSet
  [dictkeys cards highestValue setSize]
  (cond
    (empty? dictkeys) highestValue
    true (let [coll (cards (first dictkeys))]
          (if (= (count coll) setSize)
            (if (< highestValue (first dictkeys))
              (largestSet (rest dictkeys) cards (first dictkeys) setSize)
              (largestSet (rest dictkeys) cards highestValue setSize))
            (largestSet (rest dictkeys) cards highestValue setSize)))))

(defn findCollections
  [size pocket board]
  (let [allCards (combineLikeCards pocket board)]
    (largestSet (keys allCards) allCards 0 size)))

;;Board Flush
(defn largestSuite
  [dictKeys cards biggestSet]
  (cond
    (empty? dictKeys) biggestSet
    true (let [collSize (count (cards (first dictKeys)))]
          (if (> collSize biggestSet)
            (largestSuite (rest dictKeys) cards collSize)
            (largestSuite (rest dictKeys) cards biggestSet)))))

(defn suiteDetector
  [pocket board]
  (let [cards (group-by :Suite (concat pocket board))]
    (largestSuite (keys cards) cards 0)))
