function changeDivText(divId, text) {
	updateDiv(document.getElementById(divId), text);
}

function updateDiv(div, txt) {
	if(txt.startsWith("W") || txt=="EAST") {
		div.innerHTML = "";
	} else {
		div.innerHTML = txt;
	}
}

var eliminated = [ "l" ];

function addEliminated(team) {
	eliminated.push(team);
}

function isEliminated(team) {
	for (var i = 0; i < eliminated.length; i++) {
		if (team == eliminated[i])
			return true;
	}
	return false;
}

function changeBracketInfo(bracketId, fase, team1, team2, winner, games,
		betWinner, betGames, prevBet1Winner, prevBet1Games, prevBet2Winner, prevBet2Games
		, prevSeries1Winner, prevSeries1NbrGames, prevSeries2Winner, prevSeries2NbrGames) {
	// alert('changing ' + bracketId);
	entry_1 = document.getElementById('b' + bracketId + '_1_entry');
	entry_2 = document.getElementById('b' + bracketId + '_2_entry');
	bet_1 = document.getElementById('b' + bracketId + '_1');
	bet_2 = document.getElementById('b' + bracketId + '_2');
	bet_winner = document.getElementById('b' + bracketId + '_winner');
	isABet = false;

	updateDiv(entry_1, team1); // update bet1
	updateDiv(entry_2, team2); // update bet1

	if(bet_1 != null) {
		var txt = prevBet1Winner;
		if (prevBet1Winner != null && prevBet1Winner != "") {
			txt = txt + ' in ' + prevBet1Games;
			if (team1 == prevBet1Winner) {
				bet_1.style.fontWeight = "bold";
				bet_1.style.color = "green";
				if(prevBet1Games!=prevSeries1NbrGames) {
					txt = prevBet1Winner + "<font color='red' size='-2'> in " + prevBet1Games + "</font>";
				}
			} else {
				if ((team1 != "" && !team1.startsWith("W"))
						|| isEliminated(prevBet1Winner)) {
					bet_1.style.setProperty("text-decoration", "line-through");
					bet_1.style.fontWeight = "normal";
					bet_1.style.color = "red";
				}
			}
			updateDiv(bet_1, txt); // update bet1
		}
	}

	if(bet_2 != null) {
		var txt = prevBet2Winner;
		if (prevBet2Winner != null && prevBet2Winner != "") {
			txt = txt + ' in ' + prevBet2Games;
			if (team2 == prevBet2Winner) {
				bet_2.style.fontWeight = "bold";
				bet_2.style.color = "green";
				if(prevBet2Games!=prevSeries2NbrGames) {
					txt = prevBet2Winner + "<font color='red' size='-2'> in " + prevBet2Games + "</font>";
				}
			} else {
				if ((team2 != "" && !team2.startsWith("W"))
						|| isEliminated(prevBet2Winner)) {
					bet_2.style.setProperty("text-decoration", "line-through");
					bet_2.style.fontWeight = "normal";
					bet_2.style.color = "red";
				}
			}
			updateDiv(bet_2, txt); // update bet2
		}
	}


	var winner_txt = winner + ((winner != '') ? ' in ' + games : '');
	//updateDiv(bet_winner, winner_txt); // update winner box
	if (winner != "" && winner == team1) {
		entry_1.style.fontWeight = "bold";
	}
	if (winner != "" && winner == team2) {
		entry_2.style.fontWeight = "bold";
	}

	if (bracketId == 15 && betWinner != null && betWinner != "") {
		var txt = betWinner + ' in ' + betGames;
		changeDivText('champion', txt);
	}
}
