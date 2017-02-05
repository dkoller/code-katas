# Maven archetype

## Install

```
mvn install
```

## Create project from archetype

```
mvn archetype:generate 
  -DarchetypeGroupId=es.rachelcarmena
  -DarchetypeArtifactId=code-kata
  -DarchetypeVersion=1.0 
  -DgroupId=es.rachelcarmena 
  -DartifactId=example
```

## Create Eclipse files

```
mvn eclipse:eclipse
```

## NOTE

If you want to create a project through something like this:

```
rachel@repera:~$ codekata
```

create an alias in your `~/.bashrc` file:

```
alias codekata=~/path/create-kata-project.sh
```

and reload `~/.bashrc` (in order to avoid close and open a new terminal):

```
source ~/.bashrc
```
