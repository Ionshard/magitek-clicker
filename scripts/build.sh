#!/bin/bash

lein clean
lein less once
lein cljsbuild once min
