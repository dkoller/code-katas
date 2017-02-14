# Maven archetype

## Installation

```
mvn install
```

## Project creation from archetype

```
mvn archetype:generate 
  -DarchetypeGroupId=es.rachelcarmena
  -DarchetypeArtifactId=code-kata
  -DarchetypeVersion=1.0 
  -DgroupId=es.rachelcarmena 
  -DartifactId=example
```

## Eclipse files creation

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

and reload `~/.bashrc` (in order to avoid closing and opening a new terminal):

```
source ~/.bashrc
```
