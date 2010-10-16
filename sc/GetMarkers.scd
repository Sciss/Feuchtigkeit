// last mod: 03-Jun-08

(
e = Eisenkraut.default;
e.addr.connect;
)

// This example queries all markers
// of the active document, and returns
// them in the variable ~pos
// (a List of marker positions in
// sample frames)
(
~marks = [];
fork {
	var msg, rate, num, startIdx, stopIdx;
	msg = e.query( '/doc/active/timeline', \rate );
	if( msg.notNil, {
		rate = msg[ 0 ];
		msg = e.query( '/doc/active/markers', \count );
		if( msg.notNil, {
			num = msg[ 0 ];
			("Number of markers: "++num).postln;
			startIdx = 0;
			// maximum 128 markers per query, based on an estimated of maximum marker names ...
			// 128 * (32 + 4 + 5) + headerSize = ca. 5000 bytes
			stopIdx = min( num, startIdx + 128 );
			while({ startIdx < num }, {
				msg = e.get( '/doc/active/markers', [ \range, startIdx, stopIdx ]);
				if( msg.notNil, {
					msg.pairsDo({ arg pos, name;
						("Marker '"++name++"' at frame "++pos++" = "++(pos/rate).asTimeString( 0.001 )).postln;
						~marks = ~marks.add( pos -> name );
					});
				}, {
					"timeout".warn;
				});
				startIdx	= stopIdx;
				stopIdx	= min( num, startIdx + 128 );
			});
		}, {
			"timeout".warn;
		});
	}, {
		"timeout".warn;
	});
}
)

~marks.asCompileString