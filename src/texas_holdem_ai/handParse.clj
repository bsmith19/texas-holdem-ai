(ns texas-holdem-ai.handParse
  (:gen-class))

;;This file focuses on turning the text representation of the hand into a structure useful to the ai

(defn removeBrackets
  [handString]
  (apply str (remove #((set "[]") %) handString)))

(defn buildCardList
  [handString]
  (clojure.string/split handString #","))

(defn translateCardToNum
  [card]
  (case card
    \2 2
    \3 3
    \4 4
    \5 5
    \6 6
    \7 7
    \8 8
    \9 9
    \T 10
    \J 11
    \Q 12
    \K 13
    \A 14))

(defn parseCardPair
  [card]
  {:CardVal (translateCardToNum (get card 0)) :Suite (get card 1)})

(defn handParser
  [handString]
  (into [] (map parseCardPair (buildCardList (removeBrackets handString)))))
