import 'package:flutter/foundation.dart';
import '../core/api_service.dart';
import '../models/company.dart';
import '../models/company_profile.dart';
import '../models/financial_ratios.dart';
import '../models/shariah_compliance.dart';

class CompanyProvider with ChangeNotifier {
  final ApiService api = ApiService();

  // --- State ---
  List<Company> companies = [];
  bool loadingCompanies = false;

  CompanyProfile? selectedProfile;
  FinancialRatios? selectedRatios;
  List<ShariahCompliance>? selectedCompliance;
  bool loadingDetails = false;

  // --- Fetch list of companies ---
  Future<void> fetchCompanies({String? search}) async {
    loadingCompanies = true;
    notifyListeners();

    try {
      final resp = await api.get(
        '/api/companies',
        query: search != null ? {'q': search} : null,
      );
      final List data = resp.data as List;
      companies = data.map((c) => Company.fromJson(c)).toList();
    } catch (e) {
      debugPrint('Error fetching companies: $e');
    } finally {
      loadingCompanies = false;
      notifyListeners();
    }
  }

  // --- Fetch details for one company ---
  Future<void> fetchCompanyDetails(int companyId, String symbol) async {
    loadingDetails = true;
    notifyListeners();

    try {
      // Profile by symbol
      final profileResp = await api.get('/api/companyProfiles/$symbol');
      selectedProfile = CompanyProfile.fromJson(profileResp.data);

      // Ratios by companyId
      final ratiosResp = await api.get('/api/financialRatios/$companyId');
      selectedRatios = FinancialRatios.fromJson(ratiosResp.data);

      // Shariah compliance records
      final complianceResp = await api.get('/api/shariahCompliance/$companyId');
      final List complianceData = complianceResp.data as List;
      selectedCompliance =
          complianceData.map((e) => ShariahCompliance.fromJson(e)).toList();
    } catch (e) {
      debugPrint('Error fetching company details: $e');
      selectedProfile = null;
      selectedRatios = null;
      selectedCompliance = null;
    } finally {
      loadingDetails = false;
      notifyListeners();
    }
  }

  // Reset selected company data (when leaving detail page)
  void clearSelectedCompany() {
    selectedProfile = null;
    selectedRatios = null;
    selectedCompliance = null;
    notifyListeners();
  }
}
