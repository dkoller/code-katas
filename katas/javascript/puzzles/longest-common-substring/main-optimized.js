function lcs(strings) {
  var maxLength = 0;
  var fromIndex = -2;
  for (var i = 0; i < strings[0].length; i++) {
    if (strings[0].length - i <= maxLength) break;
    if (strings[1].indexOf(strings[0][i]) === -1) continue;

    var candidateLength = 1;
    var nextIndex = i + 1;
    if (i === fromIndex + 1) {
      nextIndex = fromIndex + maxLength;
      candidateLength = maxLength - 1;
    }
    for (var j = nextIndex; j < strings[0].length; j++) {
      if (strings[1].indexOf(strings[0].substr(i, candidateLength + 1)) !== -1)
        candidateLength += 1;
      else
        break;
    }
    if (candidateLength > maxLength) {
      maxLength = candidateLength;
      fromIndex = i;
    }
  }
  return strings[0].substr(fromIndex, maxLength);
}

lcs(input.split("\n")); // in ascending order of length
