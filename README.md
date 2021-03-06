[Vagrant Tutorial](http://rochesterinnyc.github.io/3134-Vagrant-Tutorial/)
---
####3134 - Data Structures in Java 
-------

This a tutorial on how to use Vagrant to create and manage virtual machines (VMs) for 3134: Data Structures in Java. We will be using VMs in this class to standardize the development environment of the students as well as the testing environment for the TAs and Professor Blaer regarding homework assignments.

-------

###Table of Contents

- [Terms/Definitions](#terms)
- [Installation](#installation)
- [Initial Setup](#setup)
- [Starting the VM](#start)
- [Shutting Down the VM](#shutdown)
- [Example Workflow](#workflow)
- [Useful Commands](#commands)
- [Demo](#demo)
- [Other Notes](#other)
- [References/Documentation](#docs)

-------

<a name="terms"></a>

###Term Definitions:

####Virtual Machine (VM)
- Implementation/Simulation of a machine through software
- "Computer within a computer"

####Vagrant
- Wrapper for virtualbox operations and VM creation, configuration, management, etc.

####Virtualbox
- Virtualization software that can create and manage Virtual Machines on your machine running a variety of different operating systems and software

####Box
- Package format for Vagrant environments that includes information like system settings, software, runtime environments, etc.

####Vagrantfile
- Configuration file that describes the virtual machine and how to configure/provision them
- Written in Ruby (but you don't need to really know Ruby to write/edit them)

####Shared Folder
- A folder/directory that is shared between different machines
- Ex. a directory shared between your laptop and the virtual machine it's running

------

<a name="installation"></a>

###Installation:

####Vagrant:

<a href="https://www.vagrantup.com/downloads" target="_blank">Vagrant Downloads</a>

####Virtualbox:

<a href="https://www.virtualbox.org/wiki/Downloads" target="_blank">Virtual Downloads</a>

Select downloads for appropriate OS. Install the appropriate packages/software for your OS.

------

<a name="setup"></a>

###Setup:

####Setting up a Box:

The box was created by Professor Cannon and details the standardized environment that the 1004 and 3134 classes will be using.
The box was modified by James Wen to include vim and emacs.

Details:

- Ubuntu 14.04 with Desktop
- Openjdk7, Eclipse, and Gimp installed
- Text Editors: Vim and emacs

\- Get 3134-VM.box file from a 3134 distributed USB flash drive or from Professor Blaer's website when it's up

\- Go to directory containing 3134-VM.box

\- Run box add command:

    vagrant box add [boxFile] --name [boxName]

Ex:
 
    vagrant box add 3134-VM.box --name 3134Box

3134Box is now the name of your box.

\- Note: Expect running this command to take a while.

####Setting up Vagrantfile:

\- Initialize vagrantfile

    vagrant init

\- Open the newly created Vagrantfile with a text editor and add the following lines of code at the bottom (above the "end" line):

This specifies which box the VM to launch should use:

    config.vm.box = "3134Box"

This allows for the GUI (if the box is set up with one) to appear:

    config.vm.provider "virtualbox" do |vb|
      vb.gui = true
    end

------

<a name="start"></a>

###Starting your VM:

\- Go to the directory that contains your Vagrantfile.

\- Run the following:

    vagrant up

\- Note: Running this command without having any VM suspended or halted will take a while to boot up the VM.

####Shared Folders:

The directory that you launched the VM from on your machine will be the the same as 
    
    /vagrant/ 

on the virtual machine. All data is shared between the two without any VM restart necessary.

------

<a name="shutdown"></a>

###Shutting Down the VM:

<a href="https://docs.vagrantup.com/v2/getting-started/teardown.html" target="_blank">Vagrant Teardown</a>

####From Your Machine:

Suspend: Saves your VM's disk storage and state. Analogous to closing your laptop hood/hibernating.

    vagrant suspend

Halt: Saves your VM's disk storage but not state. Analogous to shutting down your laptop. 

    vagrant halt

Halt: Does not save your VM's disk storage or state. Removes all traces of your VM from your machine. Analogous to destroying your laptop. 

    vagrant destroy

Note: The command to start the VM after any of these is still

    vagrant up

Though different things happen and load times will vary.
From fastest to slowest: vagrant suspend, vagrant halt, vagrant destroy

####From Inside VM:

(Similar to vagrant halt)

    shutdown -h [time]

Ex.

    shutdown -h now

------

<a name="workflow"></a>

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
- When done with programming session or hw, shut down VM with vagrant suspend, halt, or destroy

- Note: Once vagrant and virtualbox are installed and the box is added, then all you need to do to boot VMs is vagrant up with a Vagrantfile in directory.
- Note: Running vagrant up in subdirectories is also possible: <a href="https://docs.vagrantup.com/v2/vagrantfile/index.html" target="_blank">Vagrantfile Command Lookup path</a>

------

<a name="commands"></a>

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

------

<a name="demo"></a>

###OSPrint Demo:

- Clone this repo <a href="https://github.com/RochesterinNYC/3134-Vagrant-Tutorial">repo</a>, install and setup your Vagrant, Virtualbox, and Vagrantfile.
- Start up your VM.
- Run the OSPrint Java program on your local machine and your virtual machine and observe the differences in output.

Example:

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

------

<a name="other"></a>

###Other Notes:

- If you idle for too long or encounter the Ubuntu login screen (should say CS@CU), the password will be "vagrant" as this is the default password set by Vagrant.
- Please do not submit Vagrantfiles for your assignments, and **especially do not submit box files**.

------

<a name="docs"></a>

###References/Documentation:

<a href="https://docs.vagrantup.com/v2/">Vagrant</a>

<a href="https://www.virtualbox.org/wiki/Documentation">Virtualbox</a>