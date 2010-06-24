#!/bin/bash
./Exercise-6-Acceptor 5005 &
./Exercise-6-Connector venus 5005 4 < DataFile.data
