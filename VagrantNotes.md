Vagrant Tutorial
---
####3134 - Data Structures in Java 
-------

###Terms:

####Virtual Machine
-

####Vagrant
- Wrapper for virtualbox operations and VM creation, configuration, management, etc.

####Virtualbox
- Virtualization software that 

####Box

####Vagrantfile

####Shared Folder

------

###Installation:

####Vagrant:

<a href="https://www.vagrantup.com/downloads" target="_blank">Vagrant Downloads</a>

####Virtualbox:

<a href="https://www.virtualbox.org/wiki/Downloads" target="_blank">Virtual Downloads</a>

Select downloads for appropriate OS.

------

###Setup:

####Setting up Box:

\- Download package.box

\- Go to directory containing package.box

\- Run box add command:

    vagrant box add [boxFile] --name [BoxName]

Ex:
 
    vagrant box add package.box --name 3134Box

3134Box is now the name of your box.

####Setting up Vagrantfile:

\- Initialize vagrantfile

    vagrant init

\- Open the newly created Vagrantfile with a text editor and add the following lines of code at the bottom:

This specifies which box the VM to launch should use:

    config.vm.box = "3134Box"

This allows for the GUI (if the box is set up with one) to appear:

    config.vm.provider "virtualbox" do |vb|
      vb.gui = true
    end

------

###Starting your VM:

\- Go to the directory that contains your Vagrantfile.
\- Run the following:

    vagrant up

####Shared Folders:

The directory that you launched the VM from on your machine
will be the the same as 
    
    /vagrant/ 

on the virtual machine. All data is shared between the two without any VM restart necessary.

------

###Shutting Down the VM:

<a href="https://docs.vagrantup.com/v2/getting-started/teardown.html" target="_blank">Vagrant Teardown</a>

####From Your Machine:

Suspend: Saves your VM's disk storage and state. Analogous to closing your laptop hood/hibernating.

    vagrant suspend

Halt: Saves your VM's disk storage but not state. Analogous to shutting down your laptop. 

    vagrant halt

Halt: Does not save your VM's disk storage or state. Removes all traces of your VM from your machine. Analogous to destroying your laptop. 

    vagrant destroy

Note: Way to start the VM after any of these is still

    vagrant up

Though different things happen and load times will vary.
From fastest to slowest: vagrant suspend, vagrant halt, vagrant destroy

####From Inside VM:

(Similar to vagrant halt)

    shutdown -h [time]

Ex.

    shutdown -h now

------

###Example Workflow:

- Create hw/project directory.
- Either:
  - Initialize Vagrantfile and configure appropriately
  - Copy already configured Vagrantfile
- Start the VM with vagrant up
- Create/edit .java files in whichever machine with whichever text editor
Either:
  - SSH into the VM with vagrant ssh
  - Work within the GUI of the VM
- Compile, run, and test (make sure you're running these in the VM to ensure compatibility with TA/Professor grading/testing environment)
- when done with programming session or hw, shut down with vagrant suspend, halt, or destroy

- Once vagrant and virtualbox are installed, box is added, then all you need to do to boot VMs is vagrant up with a Vagrantfile in directory.
vagrant up in subdirectories is also possible: link
------

###Useful Vagrant commands:

<a href="https://docs.vagrantup.com/v2/cli/index.html" target="_blank">Vagrant Command Line Interface</a>

List all added boxes:
 
    vagrant box list

Initialize new vagrant file:

    vagrant init

Start VM:
 
    vagrant up

Add a box:

    vagrant box add [boxFile] --name [BoxName]

SSH into VM:

    vagrant ssh

Suspend VM:

    vagrant suspend

Shutdown VM:

    vagrant halt

Destroy VM:
    
    vagrant destroy

talk about .vagrant directory

    Bringing machine 'default' up with 'virtualbox' provider...
    ==> default: Importing base box '3134Box'...
    ==> default: Matching MAC address for NAT networking...
    ==> default: Setting the name of the VM: Vagrant_default_1410852242789_98187
    ==> default: Clearing any previously set network interfaces...
    ==> default: Preparing network interfaces based on configuration...
        default: Adapter 1: nat
    ==> default: Forwarding ports...
        default: 22 => 2222 (adapter 1)
    ==> default: Booting VM...
    ==> default: Waiting for machine to boot. This may take a few minutes...
        default: SSH address: 127.0.0.1:2222
        default: SSH username: vagrant
        default: SSH auth method: private key
        default: Warning: Connection timeout. Retrying...
        default: Warning: Connection timeout. Retrying...
        default: Warning: Connection timeout. Retrying...
        default: Warning: Connection timeout. Retrying...
        default: Warning: Remote connection disconnect. Retrying...
    ==> default: Machine booted and ready!
    ==> default: Checking for guest additions in VM...
    ==> default: Mounting shared folders...
        default: /vagrant => /Users/Rochester/Documents/3134-TA-Fall-2014/Vagrant


        dyn-160-39-228-106:Vagrant Rochester$ make
        javac -g OSPrint.java
        dyn-160-39-228-106:Vagrant Rochester$ make run
        java OSPrint
        This machine is running with the following properties:
        Operating System: Mac OS X 10.9.4
        Java Version: 1.7.0_67
        Architecture: x86_64
        User: Rochester
        Java Home Directory: /Library/Java/JavaVirtualMachines/jdk1.7.0_67.jdk/Contents/Home/jre

        vagrant@base2:/vagrant$ make
        make: Nothing to be done for `default'.
        vagrant@base2:/vagrant$ make run
        java OSPrint
        This machine is running with the following properties:
        Operating System: Linux 3.13.0-35-generic
        Java Version: 1.7.0_65
        Architecture: amd64
        User: vagrant
        Java Home Directory: /usr/lib/jvm/java-7-openjdk-amd64/jre


        - Notice that the make on the VM produces the "make: Nothing to be done for `default'." message because the java files were already compiled on your machine and the directory is shared so the .class files are already present when make is ran on the VM.
        - Notice the different operating systems, java version, where java is located, and the user names.
        - Architecture is actually the same (x86_64 and amd64 are just different names for same architecture)
