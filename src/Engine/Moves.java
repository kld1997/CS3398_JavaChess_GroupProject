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
		
		int trail = Long.numberOfTrailingZeros(coord);
		
		long attacks = HV(occ, coord);
		
		blockers &= attacks;
		if(blockers == 0) 
			return blockers;
		
		occ ^= blockers;
		
		attacks ^= HV(occ, coord);
		
		return attacks;	
	}
	
static public long xrayDX(long occ, long coord, long blockers) {
		
		int trail = Long.numberOfTrailingZeros(coord);
		
		long attacks = DX(occ, coord);
		
		blockers &= attacks;
		if(blockers == 0) 
			return blockers;
		
		occ ^= blockers;
		
		attacks ^= DX(occ, coord);
		
		return attacks;
	}
}