NetManProfile=$(nmcli -t  connection show --active | cut -f 01 -d ':')
# remove, if exists, current dns servers
nmcli con mod "$NetManProfile" ipv4.dns ""
# set 'manual' dns server
nmcli con mod "$NetManProfile" ipv4.ignore-auto-dns yes
# set dnsmasq as manually set dns server
nmcli con mod "$NetManProfile" ipv4.dns 127.0.0.1
# i also disabled ip6, do what u want
nmcli con mod "$NetManProfile" ipv6.method ignore
# reconnect to take effect
nmcli connection down "$NetManProfile"
nmcli connection up "$NetManProfile"
