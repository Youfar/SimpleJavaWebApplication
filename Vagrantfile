# -*- mode: ruby -*-
# vi: set ft=ruby :

VAGRANTFILE_API_VERSION = "2"

vmname="webapp"
ipaddr="192.168.100.10"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "bento/centos-6.7"
  config.vm.hostname = vmname
  config.vm.network :private_network, ip: ipaddr
  config.vm.network :forwarded_port, host: 8080, guest: 8080
  config.vm.boot_timeout = 300
  config.vm.provider :virtualbox do |vb|
    vb.name = vmname
    vb.customize ["modifyvm", :id, "--cpus", 2]
    vb.customize ["modifyvm", :id, "--memory", 1024]
  end
  config.vm.provision "shell", path: "bootstrap.sh"
end
