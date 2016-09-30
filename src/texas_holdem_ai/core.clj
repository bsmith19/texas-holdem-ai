(ns texas-holdem-ai.core
  (:use [texas-holdem-ai settings match handState action util])
  (:gen-class))

;;This is the state the game will state with
(def initialState {:gameState 0 :settingsInfo {:init true :your_bot ""} :matchInfo {:init true} :hand [] :board []})


;;This checks the type of the command and hands it off accordingly
(defn checkClass
  [commandList currentState]
  (let [type (get commandList 0) data (get commandList 1) value (get commandList 2)]
    (condp = type
      "Settings" (parseStateSettings data value currentState)
      "Match" (parseStateMatch data value currentState)
      "Action" (parseAction data value currentState)
      "debug" (log currentState) ;;This is just here for debugging purposes, prints out state obj
      (getSetting currentState "your_bot") (parseStateHand type data value currentState)
      currentState))) ;;In the case of bad input, just ignore it entirely

;;Parses line from standard in
(defn parse
  [state line]
  (if (or (empty? line) (= \# (first line)))
    state
    (checkClass (clojure.string/split line #" ") state)))

;;application entry point
(defn -main
  []
  (reduce parse initialState (line-seq (java.io.BufferedReader. *in*))))

;;(-main) ;This is for running through jars, no lein
