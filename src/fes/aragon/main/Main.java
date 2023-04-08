package fes.aragon.main;

import java.util.List;

import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;
import org.junit.platform.launcher.Launcher;

import fes.aragon.shirts.unit.BuyoutUnit;

public class Main {
	public static void main(String[] args) {
		final Launcher launcher = LauncherFactory.create();
		final LauncherDiscoveryRequest req = LauncherDiscoveryRequestBuilder.request()
				.selectors(selectClass(BuyoutUnit.class)).build();
		final SummaryGeneratingListener listener = new SummaryGeneratingListener();

		launcher.registerTestExecutionListeners(listener);
		launcher.execute(req);

		final TestExecutionSummary summary = listener.getSummary();

		final List<Failure> failures = summary.getFailures();
		System.out.println("getTestsSucceededCount() - " + summary.getTestsSucceededCount());
		failures.forEach(failure -> System.out.println("failure - " + failure.getException()));
		if (failures.size() == 0)
			System.out.println("Unit test ran succesfully with 0 falures");
	}
}
