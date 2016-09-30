# texas-holdem-ai

This is a texas holdem bot, written as an exercise to use clojure in a real situation.
For use on http://theaigames.com/competitions/texas-hold-em/

## Installation

install clojure-1.6.0+ with lein for best results first.

## Usage

git clone <URL> and run 'lein run' from the project directory
'lein test' to run included unit tests

to run without lein , using the jvm directly:
java -cp <path_to_clojure-1.x.x.jar> clojure.main <path_to_core.clj>

## Options

None

## Examples

See http://theaigames.com/competitions/texas-hold-em/getting-started

### Bugs

unit tests not complete but good enough
doesn't yet make judgements based on pot odds or intelligent detection of
opponent strategies.

## License

Copyright © 2016 brasmith19

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
