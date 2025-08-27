class ShariahCompliance {
  final String? status; // "Compliant" / "Non-Compliant"
  final String? notes;

  ShariahCompliance({this.status, this.notes});

  factory ShariahCompliance.fromJson(Map<String, dynamic> json) {
    return ShariahCompliance(
      status: json['status'],
      notes: json['notes'],
    );
  }
}
