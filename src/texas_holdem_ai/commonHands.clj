(ns texas-holdem-ai.commonHands
  (:use [texas-holdem-ai state card])
  (:gen-class))

(defn flush?
  [pocket board]
  (let [flushData (suiteDetector pocket board)]
    (if (= flushData 5)
      [true 5]
      [false flushData])))

(defn fullhouse?
  [pocket board]
  (let [tripsVal (findCollections 3 pocket board) pairVal (findCollections 2 pocket board)]
    (if (and (< 0 tripsVal) (< 0 pairVal))
      [true tripsVal pairVal]
      [false nil nil])))

(defn twopair?
  [pocket board]
  (let [pair1Val (findCollections 2 pocket board) pair2Val (findCollections 2 (remove #(= (% :CardVal) pair1Val) pocket) (remove #(= (% :CardVal) pair1Val) board))]
    (if (and (< 0 pair1Val) (< 0 pair2Val))
      [true pair1Val pair2Val]
      [false nil nil])))

(defn straight-options
  [pocket board]
  (let [sboard (sort-by :CardVal board)]
    (condp = (count board)
      0 [(connectedCards (sort-by :CardVal pocket) 0)]
      3 [(connectedCards (sort-by :CardVal (concat pocket board)) 0)]
      4 [(connectedCards (sort-by :CardVal (concat pocket [(board 0) (board 1) (board 2)])) 0) (connectedCards (sort-by :CardVal (concat pocket [(board 1) (board 2) (board 3)])) 0)]
      5 [(connectedCards (sort-by :CardVal (concat pocket [(board 0) (board 1) (board 2)])) 0) (connectedCards (sort-by :CardVal (concat pocket [(board 1) (board 2) (board 3)])) 0)
          (connectedCards (sort-by :CardVal (concat pocket [(board 2) (board 3) (board 4)])) 0)])))

(defn oldStraight?
  [pocket board]
  (let [cardCount (+ (count pocket) (count board))]
    (if (< (apply min (straight-options pocket board)) 5)
      [true]
      [false])))

(defn straight?
  [pocket board]
  (condp = (count board)
    3 [(connectedCards2 (sort-by :CardVal (concat pocket board)) 0)]
    4 [(connectedCards2 (rest (sort-by :CardVal (concat pocket board))) 0) (connectedCards2 (sort-by :CardVal (concat pocket board)) 0)]
    5 [(connectedCards2 (rest (sort-by :CardVal (concat pocket board))) 0) (connectedCards2 (sort-by :CardVal (concat pocket board)) 0) (connectedCards2 (rest (rest (sort-by :CardVal (concat pocket board)))) 0)]
    ))

(defn trips?
  [pocket board]
  (let [highVal (findCollections 3 pocket board)]
    (if (< 0 highVal)
      [true highVal]
      [false nil])))

(defn quads?
  [pocket board]
  (let [highVal (findCollections 4 pocket board)]
      (if (< 0 highVal)
        [true highVal]
        [false nil])))

(defn pair?
  [pocket board]
  (let [highVal (findCollections 2 pocket board)]
    (if (< 0 highVal)
      [true highVal]
      [false nil])))

(defn straightflush?
  [pocket board]
  (let [straightData (straight? pocket board) flushData (flush? pocket board)]
    (if (and (= (straightData 0) true) (= (flushData 0) true))
      [true]
      [false])))
