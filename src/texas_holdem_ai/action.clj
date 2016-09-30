(ns texas-holdem-ai.action
  (:use [texas-holdem-ai brain])
  (:gen-class))

;;This is the file that parses action requests and hands them off to the poker brain

;;Action b t	Request for the bot's next action within given timespan

;All move requests will orginate here.
(defn parseAction
  [type time currentState]
  (let [command (brain currentState)]
    (do
      (println (get command 0) (get command 1))
      currentState)))
