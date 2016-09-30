(ns texas-holdem-ai.match
  (:use [texas-holdem-ai handParse state])
  (:gen-class))

;;The match is data that persists through multiple hands but not permanently through execution

;;Match round i -> The number of the currently played hand, counting starts at 1
;;Match small_blind i -> The current size of the small blind
;;Match big_blind i -> The current size of the big blind
;;Match on_button b -> The name of the bot that currently has the dealer button (gets the small blind)
;;Match table [c,c,c,...] -> The cards that are currently on the table
;;Match max_win_pot i -> Total amount of chips currently in the pot (plus sidepot)
;;Match amount_to_call i -> The amount of chips your bot has to put in to call

;;Handles actual editing of the settings dictionary directly
(defn parseMatch
  [setting value matchMap]
  (if (contains? matchMap (keyword setting))
    (assoc matchMap (keyword setting) value) ;;Replace the value and build a new object
    (conj matchMap [(keyword setting) value]))) ;;build a new object with an added kvp


;;Entry point for updating or adding settings through the state object
(defn parseStateMatch
  [setting value currentState]
  (case setting
    "table" (let [cards (handParser value)] (conj currentState {:board cards :gameState (computeStateByBoard cards)}))
    (assoc currentState :matchInfo (parseMatch setting value (get currentState :matchInfo)))))

;;Shortcut method to get a specfic setting
(defn getMatch
  [currentState key]
  (get-in currentState [:matchInfo (keyword key)]))

;;Shortcut to update an existing value
(defn setMatch
  [key value currentState]
  (parseStateMatch key value currentState))

;;Shortcut to get the common cards, TODO: maybe this should be stored this way instead of converted
(defn getBoard
  [currentState]
  (get currentState :board))

(defn getAmountToCall
  [currentState]
  (Integer. (get-in currentState [:matchInfo :amount_to_call])))

(defn getBigBlind
  [currentState]
  (Integer. (get-in currentState [:matchInfo :big_blind])))
