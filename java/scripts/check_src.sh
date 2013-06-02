#!/bin/bash

for x in projects/*; do if [[ ! -d $x/src ]]; then exit 1; fi; done
