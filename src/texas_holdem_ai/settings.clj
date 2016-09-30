(ns texas-holdem-ai.settings
  (:gen-class))

;;This file is for code that creates or updates the map for game settings below.

;;Settings time_bank i -> Maximum time in milliseconds that your bot has in its time bank
;;Settings time_per_move i -> Time in milliseconds that is added to your bot's time bank each move
;;Settings hands_per_level i -> Number of hands that is played within each blind level
;;Settings starting_stack i ->Amount of chips that each bot starts the game with
;;Settings your_bot b -> The name of your bot during this match

;;Explictly set your bot and opponets names here since opp bot name is never explictly set
(defn parseBotNames
  [botName settingsInfo]
  (if (= botName "player1")
    (conj settingsInfo {:your_bot "player1" :opp_bot "player2"})
    (conj settingsInfo {:your_bot "player2" :opp_bot "player1"})))

;;Handles actual editing of the settings dictionary directly
(defn parseSetting
  [setting value settingsInfo]
  (cond
    (= setting "your_bot") (parseBotNames value settingsInfo)
    true (if (contains? settingsInfo (keyword setting))
          (assoc settingsInfo (keyword setting) value) ;;Replace the value and build a new object
          (conj settingsInfo [(keyword setting) value])))) ;;build a new object with an added kvp


;;Entry point for updating or adding settings through the state object
(defn parseStateSettings
  [setting value currentState]
  (assoc currentState :settingsInfo (parseSetting setting value (get currentState :settingsInfo))))

;;Shortcut method to get a specfic setting
(defn getSetting
  [currentState key]
  (get-in currentState [:settingsInfo (keyword key)]))

;;Shortcut methods to get bot names
(defn your_bot
  [currentState]
  (get-in currentState [:settingsInfo :your_bot]))

(defn opp_bot
  [currentState]
  (get-in currentState [:settingsInfo :your_bot]))
