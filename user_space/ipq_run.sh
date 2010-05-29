#!/bin/sh
sudo modprobe iptable_filter
sudo modprobe ip_queue
sudo iptables -A OUTPUT -j QUEUE
sudo ./ipq_simple
