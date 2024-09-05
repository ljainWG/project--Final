package com.wg.dabms.model.enums;

public enum TimeSlot {
	SLOT_8AM_830AM(1), SLOT_830AM_9AM(2), SLOT_9AM_930AM(3), SLOT_930AM_10AM(4), SLOT_10AM_1030AM(5),
	SLOT_1030AM_11AM(6), SLOT_11AM_1130AM(7), SLOT_1130AM_12PM(8), SLOT_12PM_1230PM(9), SLOT_1230PM_1PM(10),
	SLOT_2PM_230PM(11), SLOT_230PM_3PM(12), SLOT_3PM_330PM(13), SLOT_330PM_4PM(14), SLOT_4PM_430PM(15),
	SLOT_430PM_5PM(16), SLOT_5PM_530PM(17), SLOT_530PM_6PM(18), SLOT_6PM_630PM(19), SLOT_630PM_7PM(20);

	private final int slotNumber;

	TimeSlot(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public static TimeSlot fromSlotNumber(int slotNumber) {
		for (TimeSlot slot : TimeSlot.values()) {
			if (slot.getSlotNumber() == slotNumber) {
				return slot;
			}
		}
		throw new IllegalArgumentException("Invalid slot number: " + slotNumber);
	}
}
