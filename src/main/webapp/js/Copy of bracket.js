function changeDivText(divId, text) {
	elem = document.getElementById(divId);
	elem.innerHTML = text;
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

function updateDiv(div, txt, isfinal) {
	div.innerHTML = txt;
	if (isfinal) {
		// document.getElementById('champion').innerHTML =
	}
}

function changeBracketInfo(bracketId, fase, team1, team2, winner, games,
		betWinner, betGames, prevTeam1Bet, prevGames1, prevTeam2Bet, prevGames2) {
	// alert('changing ' + bracketId);
	entry_1 = document.getElementById('b' + bracketId + '_1_entry');
	entry_2 = document.getElementById('b' + bracketId + '_2_entry');
	bet_1 = document.getElementById('b' + bracketId + '_1');
	bet_2 = document.getElementById('b' + bracketId + '_2');
	bet_winner = document.getElementById('b' + bracketId + '_winner');
	isABet = false;

	/*
	 * if ((((team1.lastIndexOf('W', 0) === 0) && team1.length < 5) || team1 ==
	 * "EAST") && (prevTeam1Bet != null && prevTeam1Bet != "")) {
	 * entry_1.innerHTML = prevTeam1Bet + ' in ' + prevGames1; isABet = true; }
	 * else { entry_1.innerHTML = team1; }
	 * 
	 * if ((((team2.lastIndexOf('W', 0) === 0) && team2.length < 5) || team2 ==
	 * "EAST") && (prevTeam2Bet != null && prevTeam2Bet != "")) {
	 * entry_2.innerHTML = prevTeam2Bet + ' in ' + prevGames2; isABet = true; }
	 * else { entry_2.innerHTML = team2; }
	 */

	if (betWinner != null && betWinner != "") {

		if (winner != null && winner != "") {
			// there is a winner already
			var winner_txt = winner + ' in ' + games;
			updateDiv(bet_winner, winner_txt); // update winner box

			if (betWinner != null && betWinner != "") {
				// there is a bet
				var txt = winner + ' in ' + games;
				var betdiv = (winner == team1) ? bet_1 : bet_2;
				var div = (winner == team1) ? entry_1 : entry_2;
				if (winner != betWinner) {
					// wrong pick
					updateDiv(div, txt, bracketId == 15);
				} else {
					if (games != betGames) {
						// right pick, wrong nbr of games
						updateDiv(div, txt + '(' + betGames + ')',
								bracketId == 15);
					} else {
						// right pick and nbr of games
						updateDiv(div, txt, bracketId == 15);
					}
				}
			}
		} else {
			// not a winner yet
			if (betWinner != null && betWinner != "") {
				var txt = betWinner + ' in ' + betGames;
				// betting on 1 or 2?
				if (betWinner == team1) {
					updateDiv(entry_1, txt, bracketId == 15);
				}
				if (betWinner == team2) {
					updateDiv(entry_2, txt, bracketId == 15);
				}
			}

		}

		// if there is a winner, mark it in bold
		if (winner != null && winner != "") {
			if (team1 == winner) {
				entry_1.style.fontWeight = "bold";
				// entry_2.style.fontWeight = "normal";
			}
			if (team2 == winner) {
				// entry_1.style.fontWeight = "normal";
				entry_2.style.fontWeight = "bold";
			}
		}

		// alert(betWinner + " " + isABet + " " + team1 + " " + prevTeam1Bet);
		if ((betWinner != null && betWinner != "") || isABet) {
			// there is a bet
			if (isABet) {
				entry_1.style.color = "blue";
				if (isEliminated(prevTeam1Bet)) {
					entry_1.style
							.setProperty("text-decoration", "line-through");
				}
				entry_2.style.color = "blue";
				if (isEliminated(prevTeam2Bet)) {
					entry_2.style
							.setProperty("text-decoration", "line-through");
				}

			} else {
				if (team1 == betWinner) {
					// mark in blue
					entry_1.style.color = "blue";
					if (winner != null && winner != "" & winner != betWinner) {
						entry_2.style.color = "red";
						entry_1.style.setProperty("text-decoration",
								"line-through");
					} else
						entry_2.style.color = "black";
				}
				if (team2 == betWinner) {
					entry_2.style.color = "blue";
					if (winner != null && winner != "" & winner != betWinner) {
						entry_1.style.color = "red";
						entry_2.style.setProperty("text-decoration",
								"line-through");
					} else
						entry_1.style.color = "black";
				}
			}
		}

	}

	var knownBrackets = [ 2, 4, 8, 16, 32 ], // brackets with "perfect"
	// proportions (full fields, no
	// byes)

	teams = [ "l", "2" ]
	exampleTeams = _.shuffle([ "New Jersey Devils", "New York Islanders",
			"New York Rangers", "Philadelphia Flyers", "Pittsburgh Penguins",
			"Boston Bruins", "Buffalo Sabres", "Montreal Canadiens",
			"Ottawa Senators", "Toronto Maple Leafs", "Carolina Hurricanes",
			"Florida Panthers", "Tampa Bay Lightning", "Washington Capitals",
			"Winnipeg Jets", "Chicago Blackhawks", "Columbus Blue Jackets",
			"Detroit Red Wings", "Nashville Predators", "St. Louis Blues",
			"Calgary Flames", "Colorado Avalanche", "Edmonton Oilers",
			"Minnesota Wild", "Vancouver Canucks", "Anaheim Ducks",
			"Dallas Stars", "Los Angeles Kings", "Phoenix Coyotes",
			"San Jose Sharks", "Montreal Wanderers", "Quebec Nordiques",
			"Hartford Whalers" ]),

	bracketCount = 0;
	/*
	 * Build our bracket "model"
	 */
	function getBracket(base) {

		var closest = _.find(knownBrackets, function(k) {
			return k >= base;
		}), byes = closest - base;

		if (byes > 0)
			base = closest;

		var brackets = [], round = 1, baseT = base / 2, baseC = base / 2, teamMark = 0, nextInc = base / 2;

		for (i = 1; i <= (base - 1); i++) {
			var baseR = i / baseT, isBye = false;

			if (byes > 0 && (i % 2 != 0 || byes >= (baseT - i))) {
				isBye = true;
				byes--;
			}

			var last = _.map(_.filter(brackets, function(b) {
				return b.nextGame == i;
			}), function(b) {
				return {
					game : b.bracketNo,
					teams : b.teamnames
				};
			});

			brackets.push({
				lastGames : round == 1 ? null : [ last[0].game, last[1].game ],
				nextGame : nextInc + i > base - 1 ? null : nextInc + i,
				teamnames : round == 1 ? [ exampleTeams[teamMark],
						exampleTeams[teamMark + 1] ]
						: [ last[0].teams[_.random(1)],
								last[1].teams[_.random(1)] ],
				bracketNo : i,
				roundNo : round,
				bye : isBye
			});
			teamMark += 2;
			if (i % 2 != 0)
				nextInc--;
			while (baseR >= 1) {
				round++;
				baseC /= 2;
				baseT = baseT + baseC;
				baseR = i / baseT;
			}
		}

		renderBrackets(brackets);
	}

	/*
	 * Inject our brackets
	 */
	function renderBrackets(struct) {
		var groupCount = _.uniq(_.map(struct, function(s) {
			return s.roundNo;
		})).length;

		var group = $('<div class="group' + (groupCount + 1) + '" id="b'
				+ bracketCount + '"></div>'), grouped = _.groupBy(struct,
				function(s) {
					return s.roundNo;
				});

		for (g = 1; g <= groupCount; g++) {
			var round = $('<div class="r' + g + '"></div>');
			_
					.each(
							grouped[g],
							function(gg) {
								if (gg.bye)
									round.append('<div></div>');
								else
									round
											.append('<div><div class="bracketbox"><span class="info">'
													+ gg.bracketNo
													+ '</span><span class="teama">'
													+ gg.teamnames[0]
													+ '</span><span class="teamb">'
													+ gg.teamnames[1]
													+ '</span></div></div>');
							});
			group.append(round);
		}
		group
				.append('<div class="r'
						+ (groupCount + 1)
						+ '"><div class="final"><div class="bracketbox"><span class="teamc">'
						+ _.last(struct).teamnames[_.random(1)]
						+ '</span></div></div></div>');
		$('#brackets').append(group);

		bracketCount++;
		$('html,body').animate({
			scrollTop : $("#b" + (bracketCount - 1)).offset().top
		});
	}
}
