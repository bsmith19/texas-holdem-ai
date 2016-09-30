(ns texas-holdem-ai.util
  (:gen-class))

  ;;Log output to stderr, log file outputted from game engine
  (defn log
    [text]
    (.println *err* (pr-str text))
    text)
