#!/bin/bash
name=$1
tc qdisc add dev $name root handle 1:0 htb default 30
# Устанавливаем скорость
tc class add dev $name parent 1:0 classid 1:1 htb rate 100mbit
tc class add dev $name parent 1:1 classid 1:10 htb rate 40mbit
tc class add dev $name parent 1:1 classid 1:20 htb rate 20mbit
tc class add dev $name parent 1:1 classid 1:30 htb rate 20mbit
tc class add dev $name parent 1:1 classid 1:40 htb rate 20mbit
# Распределяем трафик
tc filter add dev $name protocol ip parent 1:0 prio 1 u32 match ip dst 172.17.0.4 flowid 1:10
tc filter add dev $name protocol ip parent 1:0 prio 1 u32 match ip dst 172.17.0.5 flowid 1:20
tc filter add dev $name protocol ip parent 1:0 prio 1 u32 match ip dst 172.17.0.6 flowid 1:30
tc filter add dev $name protocol ip parent 1:0 prio 1 u32 match ip dst 172.17.0.7 flowid 1:40
# Игнорируем ICMP
tc filter add dev $name protocol ip parent 1:0 prio 1 u32 match ip protocol 1 0xff flowid 1:1 action drop
# Для второй зоны UDP в приоретете
tc filter add dev $name protocol ip parent 1:0 prio 1 u32 match ip dst 172.17.0.5 match ip protocol 17 0xff flowid 1:20
# Для 3й зоны игнорируем SSH
tc filter add dev $name protocol ip parent 1:0 prio 1 u32 match ip dst 172.17.0.6 match ip sport 22 0xffff flowid 1:30 action drop
