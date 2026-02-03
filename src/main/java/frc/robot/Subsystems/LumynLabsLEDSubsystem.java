package frc.robot.Subsystems;

// LumynLabs LED controller imports - these are the hardware devices that control LEDs
import com.lumynlabs.devices.ConnectorXAnimate;
import com.lumynlabs.devices.ConnectorX;
import com.lumynlabs.connection.usb.USBPort;
import com.lumynlabs.connection.uart.UARTPort;

// WPILib color utilities - used to represent RGB colors
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj.util.Color;

// WPILib units system - provides type-safe units like Milliseconds
import edu.wpi.first.units.Units;

// LumynLabs LED features - text scrolling directions for LED matrices
import com.lumynlabs.domain.led.MatrixTextScrollDirection;

// SubsystemBase is the base class for all robot subsystems in WPILib's command-based framework
// A subsystem represents a distinct part of the robot (like LEDs, drivetrain, shooter, etc.)
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * LED Subsystem for controlling LumynLabs ConnectorX and ConnectorX Animate LED controllers.
 *
 * In WPILib's command-based programming:
 * - Subsystems represent physical components of the robot
 * - Each subsystem should only have one instance (singleton pattern)
 * - Commands can be scheduled to control subsystems
 * - Only one command can control a subsystem at a time (prevents conflicts)
 */
public class LumynLabsLEDSubsystem extends SubsystemBase {
    // Hardware devices - these objects represent the physical LED controllers
    // ConnectorXAnimate supports LED animations and matrix displays
    private ConnectorXAnimate cXAnimate = new ConnectorXAnimate();

    // ConnectorX is a simpler LED controller without animation features
    private ConnectorX cX = new ConnectorX();

    // Connection status flags - track whether each device successfully connected
    // These are useful for error checking and telemetry
    private boolean animateConnected;
    private boolean cxConnected;
    private boolean cxUart;
    private boolean cxUartFast;

    /**
     * Constructor - runs once when the subsystem is created (usually at robot startup).
     * This is where you initialize hardware, set default states, and configure devices.
     */
    public LumynLabsLEDSubsystem() {
        // Try to connect to the LED controllers via USB
        // USBPort.kUSB1 and kUSB2 refer to the USB ports on the RoboRIO
        animateConnected = cXAnimate.Connect(USBPort.kUSB1);
        cxConnected = cX.Connect(USBPort.kUSB2);

        // Alternative connection method: UART (serial communication)
        // The MXP port is a special expansion port on the RoboRIO
        cxUart = cX.Connect(UARTPort.MXP);

        // UART with custom baud rate (230400 bits per second for faster communication)
        cxUartFast = cX.Connect(UARTPort.MXP, 230400);

        // Set up the initial LED patterns
        initializeLEDs();
    }

    /**
     * Initialize LED patterns and colors.
     * Called during construction to set up default LED states.
     */
    private void initializeLEDs() {
        // Set a specific LED zone to a solid color
        // "left-climber" is a named zone you configure in the LumynLabs software
        // Color8Bit(255, 0, 0) = Red (R=255, G=0, B=0)
        // We wrap it in Color() because the LED API expects a Color object
        cXAnimate.leds.SetColor("left-climber", new Color(new Color8Bit(255, 0, 0)));

        // Set multiple zones at once using a group name
        // Groups let you control several LED zones together
        // Color8Bit(0, 255, 0) = Green
        cXAnimate.leds.SetGroupColor("all-climbers", new Color(new Color8Bit(0, 255, 0)));

        // Display scrolling text on an LED matrix
        // This uses a "builder pattern" - each .WithX() method adds a configuration option
        cXAnimate.leds.SetText("Hello World!")
            .ForZone("front-matrix")  // Which LED matrix to display on
            .WithColor(new Color(new Color8Bit(255, 255, 255)))  // White text
            .WithDirection(MatrixTextScrollDirection.Left)  // Scroll direction
            .WithDelay(Units.Milliseconds.of(300))  // 300ms between scroll steps (speed)
            .RunOnce(false);  // false = loop forever, true = scroll once then stop

        // Another scrolling text example using a group of matrices
        // Color8Bit(255, 255, 0) = Yellow (Red + Green)
        cXAnimate.leds.SetText("Hello!")
            .ForGroup("all-matrices")  // Apply to all matrices in this group
            .WithColor(new Color(new Color8Bit(255, 255, 0)))
            .WithDirection(MatrixTextScrollDirection.Right)
            .WithDelay(Units.Milliseconds.of(500))  // Slower scroll (500ms)
            .RunOnce(false);
    }

    /**
     * Periodic method - automatically called every 20ms (50 times per second) by the robot scheduler.
     * Use this for regularly updating subsystem state, reading sensors, or updating telemetry.
     *
     * Currently empty, but you could add things like:
     * - Checking connection status
     * - Updating LEDs based on robot state
     * - Publishing telemetry data to SmartDashboard
     */
    @Override
    public void periodic() {
        // This runs every robot loop iteration (every ~20ms)
    }

    // You can add public methods here that commands can call to control the LEDs
    // Examples:
    // public void setAllianceColor(Alliance alliance) { ... }
    // public void showAutonomousPattern() { ... }
    // public void celebrate() { ... }
}
