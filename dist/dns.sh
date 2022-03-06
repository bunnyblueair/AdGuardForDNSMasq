tee -a /etc/dnsmasq.conf << ENDdm
interface=lo
bind-interfaces
listen-address=127.0.0.1
# DNS server from OpenDns. Use yours...
server=8.8.8.8
server=1.1.1.1
cache-size=50000
ENDdm
