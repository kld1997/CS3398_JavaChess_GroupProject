//Roy Grady
package Engine;

public class Moves {														//class containing directional sliding movement
	
	static public long horizontal(long occ, long coord) {			//horizontal sliding
		
		int trail = Long.numberOfTrailingZeros(coord);
		long horizontal = (occ - coord * 2) ^ Long.reverse(Long.reverse(occ) - Long.reverse(coord) * 2);
		return horizontal & Board.rowMasks[trail / 8];
	}
	
	static public long vertical(long occ, long coord) {				//vertical sliding
		
		int trail = Long.numberOfTrailingZeros(coord);
		long vertical = ((occ&Board.colMasks[trail % 8]) - (2 * coord)) ^ Long.reverse(Long.reverse(occ&Board.colMasks[trail % 8]) - (2 * Long.reverse(coord)));
		
		return vertical & Board.colMasks[trail % 8];
	}
	
	static public long HV(long occ, long coord) {					//horizontal and vertical sliding
		
		return horizontal(occ, coord) | vertical(occ, coord);
	}
	
	static public long diagonalBLTR(long occ, long coord) {			//bottom-left to top-right diagonal sliding
		
		int trail = Long.numberOfTrailingZeros(coord);
		long bltr = ((occ&Board.bltrMasks[(trail / 8) + (trail % 8)]) - (2 * coord)) ^ Long.reverse(Long.reverse(occ&Board.bltrMasks[(trail / 8) + (trail % 8)]) - (2 * Long.reverse(coord)));
		
		return bltr & Board.bltrMasks[(trail / 8) + (trail % 8)];
	}
	
	static public long diagonalTLBR(long occ, long coord) {			//top-left to bottom-right diagonal sliding
		
		int trail = Long.numberOfTrailingZeros(coord);
		long tlbr = ((occ&Board.tlbrMasks[(trail / 8) + 7 - (trail % 8)]) - (2 * coord)) ^ Long.reverse(Long.reverse(occ&Board.tlbrMasks[(trail / 8) + 7 - (trail % 8)]) - (2 * Long.reverse(coord)));
		
		return tlbr & Board.tlbrMasks[(trail / 8) + 7 - (trail % 8)];
	}
	
	static public long DX(long occ, long coord) {					//both diagonals sliding
		
		return diagonalBLTR(occ, coord) | diagonalTLBR(occ, coord);
	}
	
	static public long HVDX(long occ, long coord) {					//all cardinal and ordinal directions sliding
		
		return HV(occ, coord) | DX(occ, coord);
	}
	
	static public long xrayHV(long occ, long coord, long blockers) {
		
		long attacks = HV(occ, coord);
		
		blockers &= attacks;
		if(blockers == 0) 
			return blockers;
		
		occ ^= blockers;
		
		attacks ^= HV(occ, coord);
		
		return attacks;	
	}
	
	static public long xrayDX(long occ, long coord, long blockers) {
		
		long attacks = DX(occ, coord);
		
		blockers &= attacks;
		if(blockers == 0) 
			return blockers;
		
		occ ^= blockers;
		
		attacks ^= DX(occ, coord);
		
		return attacks;
	}

	public static long kingPseudoMoves(long coord) {
		
		int trail = Long.numberOfTrailingZeros(coord);
		long pm = 0L;
		
		if(trail == 9) {
			pm = Board.kingMoves;
		}
		else {
			if(trail > 9) {
				pm = Board.kingMoves<<(trail - 9);
			}
			else if(trail < 9) {
				pm = Board.kingMoves>>(9 - trail);
			}
			
			if((coord&Board.colA) != 0) {
				pm &= ~Board.colH;
			}
			if((coord&Board.colH) != 0) {
				pm &= ~Board.colA;
			}
		}
		
		return pm;
	}
	
	public static long knightPseudoMoves(long coord) {
		
		int trail = Long.numberOfTrailingZeros(coord);	
		long pm = 0L;
		
		if(trail == 18) {
			pm = Board.knightMoves;
		}
		else {
			if(trail > 18) {
				pm = Board.knightMoves<<(trail - 18);
			}
			else if(trail < 18) {
				pm = Board.knightMoves>>(18 - trail);
			}
			
			if((coord&(Board.colA|Board.colB)) != 0) {
				pm &= ~(Board.colG|Board.colH);
			}
			if((coord&(Board.colG|Board.colH)) != 0) {
				pm &= ~(Board.colA|Board.colB);
			}
		}
		
		return pm;
	}
	
	public static long archerPseudoMoves(long coord) {
		
		int trail = Long.numberOfTrailingZeros(coord);	
		long pm = 0L;
		
		if(trail == 18) {
			pm = Board.archerMoves;
		}
		else {
			if(trail > 18) {
				pm = Board.archerMoves<<(trail - 18);
			}
			else if(trail < 18) {
				pm = Board.archerMoves>>(18 - trail);
			}
			
			if((coord&(Board.colA|Board.colB)) != 0) {
				pm &= ~(Board.colG|Board.colH);
			}
			if((coord&(Board.colG|Board.colH)) != 0) {
				pm &= ~(Board.colA|Board.colB);
			}
		}
		
		return pm;
	}
	
	public static long archerPseudoCaptures(long coord) {
		
		int trail = Long.numberOfTrailingZeros(coord);	
		long pm = 0L;
		
		if(trail == 18) {
			pm = Board.archerCaptures;
		}
		else {
			if(trail > 18) {
				pm = Board.archerCaptures<<(trail - 18);
			}
			else if(trail < 18) {
				pm = Board.archerCaptures>>(18 - trail);
			}
			
			if((coord&(Board.colA|Board.colB)) != 0) {
				pm &= ~(Board.colG|Board.colH);
			}
			if((coord&(Board.colG|Board.colH)) != 0) {
				pm &= ~(Board.colA|Board.colB);
			}
		}
		
		return pm;
	}
}