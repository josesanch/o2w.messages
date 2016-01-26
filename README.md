# o2w.messages

Reagent and re-frame component to display messages globally.

## Overview

    (:require [o2w.messages.core :refer [messages]])

In your view use [messages] wherever you want.

To push messages to the queue.

    (dispatch [:messages {:text "hello world"}])
    (dispatch [:messages {:text "hello world" :timeout 5000 :class "danger"}])

## Setup
In your project you would need to include the dependency.
[o2w.messages "0.1.0"]

## License

Copyright © 2015 José Sánchez Moreno

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
