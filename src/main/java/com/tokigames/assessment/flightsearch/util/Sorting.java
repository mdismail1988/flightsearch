package com.tokigames.assessment.flightsearch.util;

public enum Sorting {
		ASCENDING("ASC"), DESCENDING("DESC");
		private final String directionCode;
		
		private Sorting(String direction) {
			this.directionCode = direction;
		}
		
		public String getDirectionCode() {
			return this.directionCode;
		}
}
