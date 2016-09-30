#!/bin/sh

CODE_PATH="./src/texas_holdem_ai"

FILE_SETTINGS="$CODE_PATH/settings.clj"
FILE_UTIL="$CODE_PATH/util.clj"
FILE_STATE="$CODE_PATH/state.clj"
FILE_HANDPARSE="$CODE_PATH/handParse.clj"
FILE_HANDSTATE="$CODE_PATH/handState.clj"
FILE_MATCH="$CODE_PATH/match.clj"
FILE_CARD="$CODE_PATH/card.clj"
FILE_BETS="$CODE_PATH/bets.clj"
FILE_COMMONHANDS="$CODE_PATH/commonHands.clj"
FILE_POCKETHANDS="$CODE_PATH/pocketHands.clj"
FILE_BRAIN="$CODE_PATH/brain.clj"
FILE_ACTION="$CODE_PATH/action.clj"
FILE_CORE="$CODE_PATH/core.clj"

FILE_OUTPUT="texas_holdem_ai.clj"

cat $FILE_SETTINGS $FILE_UTIL $FILE_STATE $FILE_HANDPARSE $FILE_HANDSTATE $FILE_MATCH $FILE_CARD $FILE_BETS $FILE_COMMONHANDS $FILE_POCKETHANDS $FILE_BRAIN $FILE_ACTION $FILE_CORE > $FILE_OUTPUT
zip texas-holdem-ai.zip $FILE_OUTPUT
rm $FILE_OUTPUT